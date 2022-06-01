import propagation
import heritage
import exceptions
import json

f = open("network.json")
dictio = json.load(f)

# print(dictio)
value = propagation.mark_propagation(dictio, 'Animal', 'Bird', 'is a')
print(value)

value2 = heritage.heritage(dictio, 'Eagle')
print(value2)
f1 = open("exceptionNetwork.json")

exception = json.load(f1)
value3 = exceptions.mark_propagation_exception(exception, 'Animal', 'Bird', 'is a')
print(value3)
