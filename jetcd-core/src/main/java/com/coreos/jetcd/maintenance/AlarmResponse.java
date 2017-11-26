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

package com.coreos.jetcd.maintenance;

import com.coreos.jetcd.Maintenance;
import com.coreos.jetcd.data.AbstractResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AlarmResponse returned by {@link Maintenance#listAlarms()} contains a header
 * and a list of AlarmMember.
 */
public class AlarmResponse extends AbstractResponse<com.coreos.jetcd.api.AlarmResponse> {

  private List<AlarmMember> alarms;

  public AlarmResponse(com.coreos.jetcd.api.AlarmResponse response) {
    super(response, response.getHeader());
  }

  private static AlarmMember toAlarmMember(com.coreos.jetcd.api.AlarmMember alarmMember) {
    com.coreos.jetcd.maintenance.AlarmType type;
    switch (alarmMember.getAlarm()) {
      case NONE:
        type = com.coreos.jetcd.maintenance.AlarmType.NONE;
        break;
      case NOSPACE:
        type = com.coreos.jetcd.maintenance.AlarmType.NOSPACE;
        break;
      default:
        type = com.coreos.jetcd.maintenance.AlarmType.UNRECOGNIZED;
    }
    return new AlarmMember(alarmMember.getMemberID(), type);
  }

  /**
   * returns a list of alarms associated with the alarm request.
   */
  public synchronized List<AlarmMember> getAlarms() {
    if (alarms == null) {
      alarms = getResponse().getAlarmsList().stream()
          .map(AlarmResponse::toAlarmMember)
          .collect(Collectors.toList());
    }

    return alarms;
  }
}
