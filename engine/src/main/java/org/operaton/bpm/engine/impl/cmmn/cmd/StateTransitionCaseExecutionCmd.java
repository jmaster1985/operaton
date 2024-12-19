/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
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
package org.operaton.bpm.engine.impl.cmmn.cmd;

import java.util.Collection;
import java.util.Map;

import org.operaton.bpm.engine.impl.cmmn.CaseExecutionCommandBuilderImpl;
import org.operaton.bpm.engine.impl.cmmn.entity.runtime.CaseExecutionEntity;
import org.operaton.bpm.engine.impl.interceptor.CommandContext;

/**
 * @author Roman Smirnov
 *
 */
public abstract class StateTransitionCaseExecutionCmd extends CaseExecutionVariableCmd {

  private static final long serialVersionUID = 1L;

  public StateTransitionCaseExecutionCmd(String caseExecutionId, Map<String, Object> variables, Map<String, Object> variablesLocal,
        Collection<String> variableDeletions, Collection<String> variableLocalDeletions) {
    super(caseExecutionId, variables, variablesLocal, variableDeletions, variableLocalDeletions);
  }

  public StateTransitionCaseExecutionCmd(CaseExecutionCommandBuilderImpl builder) {
    super(builder);
  }

  @Override
  public Void execute(CommandContext commandContext) {
    super.execute(commandContext);

    CaseExecutionEntity caseExecution = getCaseExecution();

    performStateTransition(commandContext, caseExecution);

    return null;
  }

  protected abstract void performStateTransition(CommandContext commandContext, CaseExecutionEntity caseExecution);





}
