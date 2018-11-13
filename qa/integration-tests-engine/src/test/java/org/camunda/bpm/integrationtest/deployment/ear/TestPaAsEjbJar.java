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
package org.camunda.bpm.integrationtest.deployment.ear;

import org.camunda.bpm.application.impl.ejb.DefaultEjbProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.cdi.impl.util.ProgrammaticBeanLookup;
import org.camunda.bpm.integrationtest.deployment.ear.beans.NamedCdiBean;
import org.camunda.bpm.integrationtest.util.AbstractFoxPlatformIntegrationTest;
import org.camunda.bpm.integrationtest.util.DeploymentHelper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Roman Smirnov
 * @author Daniel Meyer
 *
 */
@RunWith(Arquillian.class)
public class TestPaAsEjbJar extends AbstractFoxPlatformIntegrationTest {

  /**
   *
   * test-application.ear
   *    |-- pa.jar
   *        |-- DefaultEjbProcessApplication.class
   *        |-- NamedCdiBean.class
   *        |-- AbstractFoxPlatformIntegrationTest.class
   *        |-- TestPaAsEjbJar.class
   *        |-- org/camunda/bpm/integrationtest/deployment/ear/paAsEjbJar-process.bpmn20.xml
   *        |-- META-INF/processes.xml
   *        |-- META-INF/beans.xml
   *
   *    |-- camunda-engine-cdi.jar
   *        |-- META-INF/MANIFEST.MF
   *
   */
  @Deployment
  public static EnterpriseArchive paAsEjbModule() throws Exception {

    JavaArchive processArchive1Jar = ShrinkWrap.create(JavaArchive.class, "pa.jar")
      .addClass(DefaultEjbProcessApplication.class)
      .addClass(NamedCdiBean.class)
      .addClass(AbstractFoxPlatformIntegrationTest.class)
      .addClass(TestPaAsEjbJar.class)
      .addAsResource("org/camunda/bpm/integrationtest/deployment/ear/paAsEjbJar-process.bpmn20.xml")
      .addAsResource("META-INF/processes.xml", "META-INF/processes.xml")
      .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

    return ShrinkWrap.create(EnterpriseArchive.class, "paAsEjbModule.ear")
      .addAsModule(processArchive1Jar)
      .addAsLibrary(DeploymentHelper.getEngineCdi());
  }

  @Test
  public void testPaAsEjbModule() {
    ProcessEngine processEngine = ProgrammaticBeanLookup.lookup(ProcessEngine.class);
    Assert.assertNotNull(processEngine);

    runtimeService.startProcessInstanceByKey("paAsEjbJar-process");
    Assert.assertEquals(1, runtimeService.createProcessInstanceQuery().count());
    waitForJobExecutorToProcessAllJobs();

    Assert.assertEquals(0, runtimeService.createProcessInstanceQuery().count());
  }

}
