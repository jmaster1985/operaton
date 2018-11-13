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
package org.camunda.bpm.engine.impl;

import java.util.Collections;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.impl.variable.serializer.TypedValueSerializer;
import org.camunda.bpm.engine.impl.variable.serializer.ValueFields;
import org.camunda.bpm.engine.impl.variable.serializer.VariableSerializers;
import org.camunda.bpm.engine.impl.variable.serializer.jpa.JPAVariableSerializer;
import org.camunda.bpm.engine.variable.impl.value.UntypedValueImpl;
import org.camunda.bpm.engine.variable.type.ValueType;
import org.camunda.bpm.engine.variable.value.TypedValue;

/**
 * @author Thorben Lindhauer
 *
 */
public class SingleQueryVariableValueCondition extends AbstractQueryVariableValueCondition
    implements ValueFields {

  protected String textValue;
  protected String textValue2;
  protected Long longValue;
  protected Double doubleValue;
  protected String type;

  public SingleQueryVariableValueCondition(QueryVariableValue variableValue) {
    super(variableValue);
  }

  public void initializeValue(VariableSerializers serializers) {
    TypedValue typedValue = wrappedQueryValue.getTypedValue();
    initializeValue(serializers, typedValue);
  }

  public void initializeValue(VariableSerializers serializers, TypedValue typedValue) {
    TypedValueSerializer serializer = determineSerializer(serializers, typedValue);

    if(typedValue instanceof UntypedValueImpl) {
      // type has been detected
      typedValue = serializer.convertToTypedValue((UntypedValueImpl) typedValue);
    }
    serializer.writeValue(typedValue, this);
    this.type = serializer.getName();
  }

  protected TypedValueSerializer determineSerializer(VariableSerializers serializers, TypedValue value) {
    TypedValueSerializer serializer = serializers.findSerializerForValue(value);

    if(serializer.getType() == ValueType.BYTES){
      throw new ProcessEngineException("Variables of type ByteArray cannot be used to query");
    }
    else if(serializer.getType() == ValueType.FILE){
      throw new ProcessEngineException("Variables of type File cannot be used to query");
    }
    else if(serializer instanceof JPAVariableSerializer) {
      if(wrappedQueryValue.getOperator() != QueryOperator.EQUALS) {
        throw new ProcessEngineException("JPA entity variables can only be used in 'variableValueEquals'");
      }

    }
    else {
      if(!serializer.getType().isPrimitiveValueType()) {
        throw new ProcessEngineException("Object values cannot be used to query");
      }

    }

    return serializer;
  }

  public List<SingleQueryVariableValueCondition> getDisjunctiveConditions() {
    return Collections.singletonList(this);
  }

  public String getName() {
    return wrappedQueryValue.getName();
  }

  public String getTextValue() {
    return textValue;
  }
  public void setTextValue(String textValue) {
    this.textValue = textValue;
  }
  public String getTextValue2() {
    return textValue2;
  }
  public void setTextValue2(String textValue2) {
    this.textValue2 = textValue2;
  }
  public Long getLongValue() {
    return longValue;
  }
  public void setLongValue(Long longValue) {
    this.longValue = longValue;
  }
  public Double getDoubleValue() {
    return doubleValue;
  }
  public void setDoubleValue(Double doubleValue) {
    this.doubleValue = doubleValue;
  }

  public byte[] getByteArrayValue() {
    return null;
  }

  public void setByteArrayValue(byte[] bytes) {
  }

  public String getType() {
    return type;
  }


}
