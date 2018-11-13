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
package org.camunda.bpm.container.impl.jboss.extension.resource;

import org.camunda.bpm.container.impl.jboss.extension.BpmPlatformExtension;
import org.camunda.bpm.container.impl.jboss.extension.handler.BpmPlatformSubsystemAdd;
import org.camunda.bpm.container.impl.jboss.extension.handler.BpmPlatformSubsystemRemove;
import org.jboss.as.controller.AttributeDefinition;
import org.jboss.as.controller.PersistentResourceDefinition;
import org.jboss.as.controller.operations.common.GenericSubsystemDescribeHandler;
import org.jboss.as.controller.registry.ManagementResourceRegistration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BpmPlatformRootDefinition extends PersistentResourceDefinition {

  public static final BpmPlatformRootDefinition INSTANCE = new BpmPlatformRootDefinition();

  private BpmPlatformRootDefinition() {
    super(BpmPlatformExtension.SUBSYSTEM_PATH,
        BpmPlatformExtension.getResourceDescriptionResolver(),
        BpmPlatformSubsystemAdd.INSTANCE,
        BpmPlatformSubsystemRemove.INSTANCE);
  }

  @Override
  public Collection<AttributeDefinition> getAttributes() {
    return Collections.emptyList();
  }

  @Override
  protected List<? extends PersistentResourceDefinition> getChildren() {
    List<PersistentResourceDefinition> children = new ArrayList<>();

    children.add(JobExecutorDefinition.INSTANCE);
    children.add(ProcessEngineDefinition.INSTANCE);

    return children;
  }

  @Override
  public void registerOperations(ManagementResourceRegistration resourceRegistration) {
    super.registerOperations(resourceRegistration);

    resourceRegistration.registerOperationHandler(GenericSubsystemDescribeHandler.DEFINITION, GenericSubsystemDescribeHandler.INSTANCE);
  }
}
