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
package org.operaton.bpm.qa.performance.engine.loadgenerator.tasks;

import org.operaton.bpm.engine.ProcessEngine;
import org.operaton.bpm.engine.RuntimeService;

/**
 * @author Daniel Meyer
 *
 */
public class StartProcessInstanceTask implements Runnable {

  protected ProcessEngine processEngine;
  protected String processDefinitionKey;

  public StartProcessInstanceTask(ProcessEngine processEngine, String processDefinitionKey) {
    this.processEngine = processEngine;
    this.processDefinitionKey = processDefinitionKey;
  }

  @Override
  public void run() {

    final RuntimeService runtimeService = processEngine.getRuntimeService();

    runtimeService.startProcessInstanceByKey(processDefinitionKey);

  }

}
