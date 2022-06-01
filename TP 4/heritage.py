def get_label(sem_network, node_id):
    label=[]
    for node in sem_network["nodes"] : 
        if node["id"] == node_id : 
            label.append(node["label"])
    return " ,".join(label)

def heritage(sem_network, name):
    the_end = False

    nodes = sem_network["nodes"]
    edges = sem_network["edges"]
    
    
    for node_ in nodes:
        if node_["label"] == name:
            node=node_
            break 

    inherited_arc=[]
    for edge in edges :
        if (edge["from"] == node["id"] and edge["label"] == "is a"):
            inherited_arc.append(edge["to"])
    legacy = []
    properties = []
    while not the_end:
        if(len(inherited_arc) == 0):
            legacy.append("no heritage")
            break
        n = inherited_arc.pop()

        legacy.append(get_label(sem_network, n))

        inherited_arc=[]
        for edge in edges:
            if (edge["from"] == n and edge["label"] == "is a"):
                inherited_arc.extend(edge["to"])

        properties_nodes=[]
        for edge in edges:
            if (edge["from"] == n and edge["label"] != "is a"):
                properties_nodes.append(edge)

        for pn in properties_nodes:
            properties.append(": ".join([pn["label"], get_label(sem_network, pn["to"])]))
        if len(inherited_arc) == 0:
            the_end = True

    return legacy, properties
