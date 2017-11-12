# Report Service : 

Le but de ce service est de gerer les evidences envoyé par els employées.

### Pardigm : Document 
### events : "Append,List,Create,ListByRptID,ListByEmPID,submit"
### Append : Permet d'ajouter une ou plusieurs évidences sur un rapport qui existe deja 

Exmeple de requete : 
{ 
"event": "Append",
"file": "image1",
"idBusinessTravel": "1",
"fin": "true",
"spending_no": 1,
"idEmploye": "1",
"Montant" : 520,
"duree_BT":3
}

## RESPONSE EXAMPLE : 
	Si le rapport est complet et il est Validé ( Le cout total < Quota ) le service renvoie le rapport en format json.
		### pour la requete suivante : { "event": "Append",	
									"file": "image1",
									"idBusinessTravel": "1",
									"fin": "true",
									"spending_no": 1,
									"idEmploye": "4",
									"Montant" : 520,
									"duree_BT":10
									}
		### le resultat est le suivant : 
		{
    "report": {
        "ETAT": "Valide",
        "total_cost": 520,
        "duree_BT": 0,
        "report_id": 4,
        "employee_id": 26,
        "complete": true,
        "evidences": [
            {
                "evidence_no": 1,
                "amount": 520,
                "evidence_desc": "image1"
            }
        ],
        "BusnessTravelId": 1
    }
}
	
	
* Si le rapport n'est pas encore complet, la reponse suivante est renvoyé :
	{
    "Update result": "success"
	}


### Create : Permet de creer un nouveau rapport à partir de l'id de l'employé : 

 {
	"event":"Create",
    "employee_id" : "1"
	
}

###delete : Permet de supprimer un rapport qui existe deja:

{
"event":"delete"
"report_id":1
}

###List : permet de lister tout les rapports sur la base des données :
{
	"event":"List",
	
}
#### Liste a aussi un param qui permet de lister seulement les rapport qui sont terminés ou bien non terminées 
{
  "event":"List",
  "submitted":false  // dans ce cas la requete renvoie la list des rapports qui ne sont pas encore términés .
}
{
  "event":"List",
  "valide"=false  // dans ce cas la requete renvoie la list des rapports qui sont terminées , et qui ne sont pas encore validés par le manager .
}

###Submit: Permet de changer l'etat d'un rapport de non termnié à términé :

{
	"event":"Submit",
	
}