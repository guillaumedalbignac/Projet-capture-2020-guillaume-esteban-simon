<?php
require "BaseDeDonnees.php";

class DAO{
    
    public static function listerHumidites(){
        $MESSAGE_SQL_LISTER_HUMIDITES = "SELECT date_part('hour',moment) as heures, MAX(tauxhumidite) as maximum, 
        MIN(tauxhumidite) as minimum, AVG(tauxhumidite) as moyenne FROM humidite WHERE date_part('day', moment) =
        date_part('day', moment) GROUP BY date_part('hour',moment)";
        $baseDeDonnees = BaseDeDonnees::getConnexion();
        $requetteListerHumidites = $baseDeDonnees->prepare($MESSAGE_SQL_LISTER_HUMIDITES);
        $requetteListerHumidites->execute();
        $listeHumidites = $requetteListerHumidites->fetchAll(PDO::FETCH_OBJ);

        return $listeHumidites;
    }

    public static function listerHumiditesMoyennes(){
        $MESSAGE_SQL_LISTER_PRODUIT_PAR_ID = "SELECT id, titre, description, prix, image FROM produit WHERE id=".$id.";";
        $baseDeDonnees = BaseDeDonnees::getConnexion();
        $requetteListerProduitsParId = $baseDeDonnees->prepare($MESSAGE_SQL_LISTER_PRODUIT_PAR_ID);
        //$requetteListerProduitsParId->bindParam(':id', $id, PDO::PARAM_INT);
        $requetteListerProduitsParId->execute();
    
        $produit = $requetteListerProduitsParId->fetch();
        return $produit;
    }
}
?>