def get_label(sem_net, node, link):
    # we retrieve the info from the dict generator from the json file
    #separate the edges and nodes
    node_link_arcs=[]
    for edge in sem_net["edges"]:
        if (edge["to"] == node["id"] and edge["label"] == link):
            node_link_arcs.append(edge["from"])

    #we take their labels for an easy search
    node_link_arcs_label = []
    for node in sem_net["nodes"]:
        if node["id"] in node_link_arcs:
            node_link_arcs_label.append(node["label"])

    reponse = "there is a link between nodes : " + ", ".join(node_link_arcs_label)
    return reponse

def mark_propagation_exception(sem_net, node1, node2, link):
    nodes = sem_net["nodes"]

    solutions_found = []

    for i in range(min(len(node1),len(node2))):
        solution_found = False
        for node in nodes:
            if node["label"] == node1:
                val1 = node
                break
      
        for node in nodes:
            if node["label"] == node2:
                val2 = node
                break

        edges = sem_net["edges"]
        mark_prop_arcs = []
        for edge in edges : 
            if (edge["to"] == val1["id"] and edge["label"] == "is a" and (edge["edge_type"] != "exception" )) :
                mark_prop_arcs.append(edge)

        while len(mark_prop_arcs) != 0 and not solution_found:
            temp_node = mark_prop_arcs.pop()
            temp_node_contains_edges=[]
            for edge in edges : 
                if (edge["from"] == temp_node["from"] and edge["label"] == link and (edge["edge_type"] != "exception" )):
                    temp_node_contains_edges.append(edge)

            solution_found = any(d['to'] == val2["id"] for d in temp_node_contains_edges)

            if not solution_found:
                is_a_temp=[]
                for edge in edges : 
                    if (edge["to"] == temp_node["from"] and edge["label"] == "is a" and (edge["edge_type"] != "exception" )):
                        is_a_temp.append(edge)
                mark_prop_arcs.extend(is_a_temp)

        solutions_found.append(get_label(sem_net, val2, link) if solution_found else "no link found")
        
    
    return(solutions_found)
