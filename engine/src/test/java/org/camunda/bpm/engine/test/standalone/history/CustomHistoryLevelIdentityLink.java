/*
 * Copyright © 2012 - 2018 camunda services GmbH and various authors (info@camunda.com)
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
package org.camunda.bpm.engine.test.standalone.history;

import java.util.List;

import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.impl.history.event.HistoryEventType;
import org.camunda.bpm.engine.impl.history.event.HistoryEventTypes;

public class CustomHistoryLevelIdentityLink implements HistoryLevel {

  private List<HistoryEventTypes> eventTypes;

  public CustomHistoryLevelIdentityLink() {
  }

  public CustomHistoryLevelIdentityLink(List<HistoryEventTypes> eventTypes) {
    this.eventTypes = eventTypes;
  }

  public int getId() {
    return 91;
  }

  public String getName() {
    return "aCustomHistoryLevelIL";
  }

  public List<HistoryEventTypes> getEventTypes() {
    return eventTypes;
  }

  public void setEventTypes(List<HistoryEventTypes> eventTypes) {
    this.eventTypes = eventTypes;
  }

  public boolean isHistoryEventProduced(HistoryEventType eventType, Object entity) {
    if (eventTypes != null) {
      for (HistoryEventTypes eventTypeConfig : eventTypes) {
        if (eventType.equals(eventTypeConfig)) {
          return true;
        }
      }
    }
    if (eventType.equals(HistoryEventTypes.TASK_INSTANCE_CREATE)) {
      return true;
    }
    return false;
  }
}
