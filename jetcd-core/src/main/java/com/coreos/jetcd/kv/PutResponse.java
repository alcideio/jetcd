/**
 * Copyright 2017 The jetcd authors
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

package com.coreos.jetcd.kv;

import com.coreos.jetcd.data.AbstractResponse;
import com.coreos.jetcd.data.KeyValue;

public class PutResponse extends AbstractResponse<com.coreos.jetcd.api.PutResponse> {

  public PutResponse(com.coreos.jetcd.api.PutResponse putResponse) {
    super(putResponse, putResponse.getHeader());
  }

  /**
   * return previous key-value pair.
   */
  public KeyValue getPrevKv() {
    return new KeyValue(getResponse().getPrevKv());
  }

  public boolean hasPrevKv() {
    return getResponse().hasPrevKv();
  }
}
