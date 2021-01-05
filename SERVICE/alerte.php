<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);

    $usager = 'postgres';
    $motdepasse = 'motdepasse';
    $hote = 'localhost';
    $base = 'normandiemeteo';

    $dsn = 'pgsql:dbname='.$base.';host=' . $hote;
    $basededonnees = new PDO($dsn, $usager, $motdepasse);

    $SQL_LISTE_HUMIDITE = "SELECT tauxhumidite FROM humidite ORDER BY id ASC LIMIT 1";
    $requete = $basededonnees->prepare($SQL_LISTE_HUMIDITE);
    $requete->execute();
    $humidites = $requete->fetchAll();
    ?><?php

    header ("Content-Type:text/xml");
    echo '<?xml version="1.0" encoding="UTF-8"?>'; 

    $valeurSeuil = 60;
    $activerAlerte = 0;

    if($humidites >= $valeurSeuil){
        $activerAlerte = 1;
    }else{
        $activerAlerte = 0;
    }

    //print_r($humidites);
    ?>
    
    <humidite>
        <mobile>
            <alerte><?=$activerAlerte?></alerte>  
	        <seuil><?=$valeurSeuil?></seuil>  
        </mobile>
    </humidite>
