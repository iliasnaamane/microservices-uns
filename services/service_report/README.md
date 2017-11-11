# Report Service : 

Le but de ce service est de gerer les evidences envoyé par els employées.

## Pardigm : Document 
## events : "Append, Create ,List,ListById, delete"
### Append : Permet d'ajouter une ou plusieurs évidences sur un rapport qui existe deja 

Exmeple de requete : 
{
	"event":"Append",
	"report_id":"3",
	"evidences":[
				{
            "evidence_no" :"1",
            "evidence_desc" : "7 nights in hotel La brestia , Brest",
            "amount" : 400
				},
				{
            "evidence_no" : "2",
            "evidence_desc" : "7 nights in hotel La brestia , Brest",
            "amount" : 190
				}
		     ]
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

###Submit: Permet de changer l'etat d'un rapport de non termnié à términé :

{
	"event":"Submit",
	
}
