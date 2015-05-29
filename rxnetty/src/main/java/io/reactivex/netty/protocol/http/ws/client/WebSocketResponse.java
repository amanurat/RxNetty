/*
 * Copyright 2015 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.reactivex.netty.protocol.http.ws.client;

import io.netty.handler.codec.http.HttpHeaders.Names;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;
import io.reactivex.netty.protocol.http.ws.WebSocketConnection;
import rx.Observable;

public abstract class WebSocketResponse<T> extends HttpClientResponse<T> {

    public abstract Observable<WebSocketConnection> getWebSocketConnection();

    public String getAcceptedSubProtocol() {
        return getHeader(Names.SEC_WEBSOCKET_PROTOCOL);
    }

    public boolean isUpgraded() {
        return getStatus().equals(HttpResponseStatus.SWITCHING_PROTOCOLS)
               && containsHeader(Names.CONNECTION, Values.UPGRADE, false)
               && containsHeader(Names.UPGRADE, Values.WEBSOCKET, false)
               && containsHeader(Names.SEC_WEBSOCKET_ACCEPT);
    }
}