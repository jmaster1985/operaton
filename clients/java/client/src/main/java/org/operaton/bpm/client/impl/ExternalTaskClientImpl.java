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
package org.operaton.bpm.client.impl;

import org.operaton.bpm.client.ExternalTaskClient;
import org.operaton.bpm.client.topic.TopicSubscriptionBuilder;
import org.operaton.bpm.client.topic.impl.TopicSubscriptionBuilderImpl;
import org.operaton.bpm.client.topic.impl.TopicSubscriptionManager;

/**
 * @author Tassilo Weidner
 */
public class ExternalTaskClientImpl implements ExternalTaskClient {

  protected TopicSubscriptionManager topicSubscriptionManager;

  public ExternalTaskClientImpl(TopicSubscriptionManager topicSubscriptionManager) {
    this.topicSubscriptionManager = topicSubscriptionManager;
  }

  @Override
  public TopicSubscriptionBuilder subscribe(String topicName) {
    return new TopicSubscriptionBuilderImpl(topicName, topicSubscriptionManager);
  }

  @Override
  public void stop() {
    topicSubscriptionManager.stop();
  }

  @Override
  public void start() {
    topicSubscriptionManager.start();
  }

  @Override
  public boolean isActive() {
    return topicSubscriptionManager.isRunning();
  }

  public TopicSubscriptionManager getTopicSubscriptionManager() {
    return topicSubscriptionManager;
  }

}
