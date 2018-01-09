/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camunda.bpm.engine.impl.runtime;

import org.camunda.bpm.engine.impl.ConditionCorrelationBuilderImpl;
import org.camunda.bpm.engine.variable.VariableMap;

public class ConditionSet {

  protected final String businessKey;
  protected final String processInstanceId;
  protected final String processDefinitionId;
  protected final VariableMap variables;
  protected final String tenantId;
  protected final boolean isTenantIdSet;

  public ConditionSet(ConditionCorrelationBuilderImpl builder) {
    this.businessKey = builder.getBusinessKey();
    this.processInstanceId = builder.getProcessInstanceId();
    this.processDefinitionId = builder.getProcessDefinitionId();
    this.variables = builder.getVariables();
    this.tenantId = builder.getTenantId();
    this.isTenantIdSet = builder.isTenantIdSet();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public String getProcessInstanceId() {
    return processInstanceId;
  }

  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  public VariableMap getVariables() {
    return variables;
  }

  public String getTenantId() {
    return tenantId;
  }

  public boolean isTenantIdSet() {
    return isTenantIdSet;
  }

  @Override
  public String toString() {
    return "ConditionSet [businessKey=" + businessKey + ", processInstanceId=" + processInstanceId + ", processDefinitionId=" + processDefinitionId
        + ", processInstanceVariables=" + variables + ", tenantId=" + tenantId + ", isTenantIdSet=" + isTenantIdSet + "]";
  }

}
