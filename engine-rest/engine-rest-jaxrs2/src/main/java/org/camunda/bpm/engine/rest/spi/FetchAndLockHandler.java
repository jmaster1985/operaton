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
package org.camunda.bpm.engine.rest.spi;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.dto.externaltask.FetchExternalTasksExtendedDto;

import javax.ws.rs.container.AsyncResponse;

/**
 * SPI supposed to replace the default implementation of the long-polling fetch and lock handler
 *
 * @author Tassilo Weidner
 */
public interface FetchAndLockHandler {

  /**
   * Receives a notification that the engine rest web application initialization has been started
   */
  void start();

  /**
   * Receives a notification that the engine rest web application is about to be shut down
   */
  void shutdown();

  /**
   * Invoked if a fetch and lock request has been sent by the client
   *
   * @param dto which is supposed to hold the payload
   * @param asyncResponse provides means for asynchronous server side response processing
   * @param processEngine provides the process engine context of the respective request
   */
  void addPendingRequest(FetchExternalTasksExtendedDto dto, AsyncResponse asyncResponse, ProcessEngine processEngine);

}
