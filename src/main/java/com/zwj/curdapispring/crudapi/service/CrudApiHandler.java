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

import com.alibaba.fastjson.JSONObject;
import com.zwj.curdapispring.controller.resp.Response;
import com.zwj.curdapispring.crudapi.ApiConfig;
import com.zwj.curdapispring.crudapi.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class CrudApiHandler {
    private final ApiConfig apiConfig;

    public Response<Object> handle(HttpServletRequest req) throws SQLException, IOException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder();
        RequestHandler.handle(req, sb, apiConfig);
        return Response.ok(JSONObject.parseObject(sb.toString()));
    }

}
