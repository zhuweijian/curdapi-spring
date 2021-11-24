/*
 *  Copyright (c) 2017. I.Kolchagov, All rights reserved.
 *  Contact: I.Kolchagov (kolchagov (at) gmail.com)
 *
 *  The contents of this file is licensed under the terms of LGPLv3 license.
 *  You may read the the included file 'lgpl-3.0.txt'
 *  or https://www.gnu.org/licenses/lgpl-3.0.txt
 *
 *  Software distributed under the License is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the License.
 *
 *  The project uses 'fluentsql' internally, licensed under Apache Public License v2.0.
 *  https://github.com/ivanceras/fluentsql/blob/master/LICENSE.txt
 *
 */

package com.zwj.curdapispring.crudapi.service;

import com.zwj.curdapispring.crudapi.ApiConfig;
import com.zwj.curdapispring.crudapi.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor
public class CrudApiConfig {
    private static final Pattern TAG_FILTER = Pattern.compile("(<script>|</script>)");

    @Value("${mysql.user}")
    private String user;

    @Value("${mysql.password}")
    private String password;

    @Value("${mysql.dbName}")
    private String dbName;

    @Value("${mysql.host}")
    private String host;

    @Value("${mysql.dataSource}")
    private String dataSource;

    @Bean
    public ApiConfig getApiConfig() {
        return new ApiConfig(user, password, dbName, host, dataSource) {
            @Override
            public boolean columnAuthorizer(RequestHandler.Actions action, String database, String table, String column) {
                return !("password".equals(column) && RequestHandler.Actions.LIST.equals(action));
            }

            @Override
            public String[] recordFilter(RequestHandler.Actions action, String database, String table) {
                return "posts".equals(table) ? new String[]{"id,neq,13"} : null;
            }

            @Override
            public Object tenancyFunction(RequestHandler.Actions action, String database, String table, String column) {
                return "users".equals(table) && "id".equals(column) ? 1 : null;
            }

            @Override
            public Object inputSanitizer(RequestHandler.Actions action, String database, String table, String column, String type, Object value, HttpServletRequest context) {
                return value instanceof String ? TAG_FILTER.matcher(((String) value)).replaceAll("") : value;
            }

            @Override
            public Object inputValidator(RequestHandler.Actions action, String database, String table, String column, String type, Object value, HttpServletRequest context) {
//                    ($column=='category_id' && !is_numeric($value))?'must be numeric':true;
                return "category_id".equals(column) && !(value instanceof Long) ? "must be numeric" : true;
            }

            @Override
            public RequestHandler.Actions before(RequestHandler.Actions action, String database, String table, String[] ids, Map<String, Object> input) {
                if ("products".equals(table)) {
                    if (action == RequestHandler.Actions.CREATE) {
                        input.put("created_at", "2013-12-11 10:09:08");
                    } else if (action == RequestHandler.Actions.DELETE) {
                        action = RequestHandler.Actions.UPDATE;
                        input.put("deleted_at", "2013-12-11 11:10:09");
                    }
                }
                return action;
            }
        };
    }
}
