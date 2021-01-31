<?php
    require "./accesseur/DAO.php";
    $listeHumidite = DAO::listerHumiditesAlerte();
    ?><?php

    header ("Content-Type:text/xml");
    echo '<?xml version="1.0" encoding="UTF-8"?>'; 

    $valeurSeuil = 60;
    $activerAlerte = 0;

    if($listeHumidite >= $valeurSeuil){
        $activerAlerte = 1;
    }else{
        $activerAlerte = 0;
    }
    
    ?>
    
    <humidite>
        <mobile>
            <alerte><?=$activerAlerte?></alerte>  
	        <seuil><?=$valeurSeuil?></seuil>  
        </mobile>
    </humidite>
