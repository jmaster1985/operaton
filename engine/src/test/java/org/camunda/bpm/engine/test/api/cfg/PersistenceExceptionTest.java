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
package org.camunda.bpm.engine.test.api.cfg;

import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.db.sql.DbSqlSessionFactory;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.RequiredHistoryLevel;
import org.camunda.bpm.engine.test.util.ProcessEngineTestRule;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Svetlana Dorokhova.
 */
@RequiredHistoryLevel(ProcessEngineConfiguration.HISTORY_AUDIT)
public class PersistenceExceptionTest {

  public ProcessEngineRule engineRule = new ProcessEngineRule(true);
  public ProcessEngineTestRule testRule = new ProcessEngineTestRule(engineRule);

  private RuntimeService runtimeService;

  @Rule
  public RuleChain ruleChain = RuleChain.outerRule(engineRule).around(testRule);

  @Before
  public void init() {
    runtimeService = engineRule.getRuntimeService();
  }

  @Test
  public void testPersistenceExceptionContainsRealCause() {
    Assume.assumeFalse(engineRule.getProcessEngineConfiguration().getDatabaseType().equals(DbSqlSessionFactory.MARIADB));
    StringBuffer longString = new StringBuffer();
    for (int i = 0; i < 100; i++) {
      longString.append("tensymbols");
    }
    final BpmnModelInstance modelInstance = Bpmn.createExecutableProcess("process1").startEvent().userTask(longString.toString()).endEvent().done();
    testRule.deploy(modelInstance);
    try {
      runtimeService.startProcessInstanceByKey("process1").getId();
      fail("persistence exception is expected");
    } catch (ProcessEngineException ex) {
      assertTrue(ex.getMessage().contains("insertHistoricTaskInstanceEvent"));
    }
  }

}
