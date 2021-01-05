<?php
    /*error_reporting(E_ALL);
    ini_set('display_errors', 1);

    $usager = 'postgres';
    $motdepasse = 'motdepasse';
    $hote = 'localhost';
    $base = 'normandiemeteo';

    $dsn = 'pgsql:dbname='.$base.';host=' . $hote;
   $basededonnees = new PDO($dsn, $usager, $motdepasse);

    $SQL_LISTE_HUMIDITE = "SELECT * FROM humidite";
    $requete = $basededonnees->prepare($SQL_LISTE_HUMIDITE);
    $requete->execute();
    $humidites = $requete->fetchAll();*/
    ?><?php

    header ("Content-Type:text/xml");
    echo '<?xml version="1.0" encoding="UTF-8"?>';
    ?>
    
    <humidite>
        <mobile>
            <alerte>1</alerte>  
	        <seuil>60</seuil>  
        </mobile>
    </humidite>
