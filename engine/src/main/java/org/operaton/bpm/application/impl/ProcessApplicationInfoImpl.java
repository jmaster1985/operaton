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
package org.operaton.bpm.application.impl;

import java.util.List;
import java.util.Map;

import org.operaton.bpm.application.ProcessApplicationDeploymentInfo;
import org.operaton.bpm.application.ProcessApplicationInfo;

/**
 * @author Daniel Meyer
 * 
 */
public class ProcessApplicationInfoImpl implements ProcessApplicationInfo {

  protected String name;
  protected List<ProcessApplicationDeploymentInfo> deploymentInfo;
  protected Map<String, String> properties;

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public List<ProcessApplicationDeploymentInfo> getDeploymentInfo() {
    return deploymentInfo;
  }

  public void setDeploymentInfo(List<ProcessApplicationDeploymentInfo> deploymentInfo) {
    this.deploymentInfo = deploymentInfo;
  }

  @Override
  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }

}
