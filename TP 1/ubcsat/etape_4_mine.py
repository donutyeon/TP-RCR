import os
import subprocess

from sqlalchemy import false

def negation_litteral(litterale):
    # premierement on separe le litterale en plusieurs partie
    litterale = litterale.split(" ")
    #on convertit en entier en enlevant le 0
    litterale = [int(i) for i in litterale if i != "0"]
    #la négation : 
    litterale = [-i for i in litterale]
    negation_litterale = ""
    for i in litterale:
        negation_litterale += "{} 0\n".format(i)
    # on retoune la negation du litteralee, sa taille, ainsi que son nombre max 
    return negation_litterale, len(litterale), max([abs(i) for i in litterale])

def insertion_litterale(cnf_path_file, litterale):
    negation_litterale, nb_lignes, var_max = negation_litteral(litterale)
    #initialisation de la base de connaissance 
    with open(cnf_path_file, 'r') as cnf_file:
        BC = cnf_file.read()
    #nous traitons le fichier cnf en enlevant tout ce qui est commentaire, 
    # sauts de lignes, ou meme espacement
    BC = BC.split("\n")
    i = 0
    while (BC[i] == "" or BC[i][0] != "p"):
        i+=1
    BC[i].replace("\t", " ")

    #réajouter les clauses apres le traitement !
    f1, f2, var_nb, nb_clause = [f for f in BC[i].split(" ") if f!=""]
    nb_clause, var_nb = (int(nb_clause), int(var_nb))
    nb_clause += nb_lignes
    #test de nombre des clauses dans le fichier
    if var_max > var_nb:
        var_nb = var_max

    BC[i] = " ".join([f1, f2, str(var_nb), str(nb_clause)])
    BC = "\n".join(BC)
    if BC[-1] != '\n':
        BC += '\n'
    #ajout de la negation du litterale 
    BC += negation_litterale
    temp_BC = os.path.basename(cnf_path_file)[:-4] + "_temporary.cnf"
    with open(temp_BC, 'w') as temporary_cnf:
        temporary_cnf.write(BC)

def solver(cnf_path_file):
    return subprocess.call("ubcsat "+ "-alg " +  "saps " + "-i " + f"{cnf_path_file} " + "-solve", shell=True)

def satisfiable(cnf_path_file):    
    solvable=solver(cnf_path_file)
    if(solvable==0):
        return True
    return False
    #return not (b"No Solution found" in solver(cnf_path_file))

def algorithme(cnf_path_file,litterale):
    #test de la satisfiabilite
    if satisfiable(cnf_path_file):
        #insertion du litterale
        insertion_litterale(cnf_path_file, litterale)
        temp_BC = os.path.basename(cnf_path_file)[:-4] + "_temporraire.cnf"
        #test de la satisfiabilite
        if satisfiable(temp_BC):
            print("la base de connaissance {} infére le litterale {}".format(os.path.basename(cnf_path_file), litterale))
        else:
            print("la base de connaissance {} n'infére pas le litterale {}".format(os.path.basename(cnf_path_file), litterale))
        # supprimer le fichier temporraire
        #os.remove(temp_BC)
    else: #raise of error !
        raise ValueError("veuillez introduire une Base de Connaissance satisfiable svp !")

if __name__ == "__main__":
    os.chdir('./ubcsat')
    os.system("start cmd /K py <ubcsat> –V <variant string> -p <ubcsat>")
    algorithme("sample.cnf","-4 6 2 0")
