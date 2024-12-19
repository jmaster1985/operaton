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
package org.operaton.bpm.engine.test.api.form;

import org.operaton.bpm.engine.impl.form.validator.FormFieldValidationException;
import org.operaton.bpm.engine.impl.form.validator.FormFieldValidator;
import org.operaton.bpm.engine.impl.form.validator.FormFieldValidatorContext;

/**
 * @author Thomas Skjolberg
 *
 */
public class CustomValidatorWithDetail implements FormFieldValidator {

  public CustomValidatorWithDetail() {
    System.out.println("CREATED");
  }

  @Override
  public boolean validate(Object submittedValue, FormFieldValidatorContext validatorContext) {
    if (submittedValue == null) {
      return true;
    }

    if (submittedValue.equals("A") || submittedValue.equals("B")) {
      return true;
    }

    if (submittedValue.equals("C")) {
      // instead of returning false, use an exception to specify details about
      // what went wrong
      throw new FormFieldValidationException("EXPIRED", "Unable to validate " + submittedValue);
    }

    // return false in the generic case
    return false;
  }

}
