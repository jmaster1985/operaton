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
package org.camunda.bpm.engine.impl.persistence.entity;

import org.camunda.bpm.engine.history.CleanableHistoricBatchReportResult;

public class CleanableHistoricBatchesReportResultEntity implements CleanableHistoricBatchReportResult {

  protected String batchType;
  protected Integer historyTimeToLive;
  protected long finishedBatchesCount;
  protected long cleanableBatchesCount;

  @Override
  public String getBatchType() {
    return batchType;
  }

  public void setBatchType(String batchType) {
    this.batchType = batchType;
  }

  @Override
  public Integer getHistoryTimeToLive() {
    return historyTimeToLive;
  }

  public void setHistoryTimeToLive(Integer historyTimeToLive) {
    this.historyTimeToLive = historyTimeToLive;
  }

  @Override
  public long getFinishedBatchesCount() {
    return finishedBatchesCount;
  }

  public void setFinishedBatchesCount(long finishedBatchCount) {
    this.finishedBatchesCount = finishedBatchCount;
  }

  @Override
  public long getCleanableBatchesCount() {
    return cleanableBatchesCount;
  }

  public void setCleanableBatchesCount(long cleanableBatchCount) {
    this.cleanableBatchesCount = cleanableBatchCount;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "[batchType = " + batchType
        + ", historyTimeToLive = " + historyTimeToLive
        + ", finishedBatchesCount = " + finishedBatchesCount
        + ", cleanableBatchesCount = " + cleanableBatchesCount
        + "]";
  }
}
