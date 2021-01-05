<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);

    $usager = 'postgres';
    $motdepasse = 'motdepasse';
    $hote = 'localhost';
    $base = 'normandiemeteo';

    $dsn = 'pgsql:dbname='.$base.';host=' . $hote;
    $basededonnees = new PDO($dsn, $usager, $motdepasse);

    //$SQL_LISTE_HUMIDITE = "SELECT * FROM humidite";
    $SQL_LISTE_HUMIDITE = "SELECT date_part('hour',moment) as heures, MAX(tauxhumidite) as maximum, MIN(tauxhumidite) as minimum,
    AVG(tauxhumidite) as moyenne FROM humidite WHERE date_part('day', moment) = date_part('day', moment) GROUP BY date_part('hour',moment)";
    $requete = $basededonnees->prepare($SQL_LISTE_HUMIDITE);
    $requete->execute();
    $humidites = $requete->fetchAll(PDO::FETCH_OBJ);

    $SQL_MOYENNES = "SELECT MAX(tauxhumidite) as maximum, MIN(tauxhumidite) as minimum, 
    AVG(tauxhumidite) as moyenne FROM humidite LIMIT 24";
    $requete2 = $basededonnees->prepare($SQL_MOYENNES);
    $requete2->execute();
    $moyennes = $requete2->fetchAll(PDO::FETCH_OBJ);
    ?><?php

    header ("Content-Type:text/xml");
    echo '<?xml version="1.0" encoding="UTF-8"?>';

    //print_r($moyennes);
    foreach ($moyennes as $moyenne) 
    {
    ?>    
        <humidite>
            <bureau>
                <jour>
                    <min><?=$moyenne->minimum?></min>
                    <moyenne><?=round($moyenne->moyenne)?></moyenne>  
                    <max><?=$moyenne->maximum?></max>  
<?php
    }
    //print_r($humidites);
    foreach ($humidites as $humidite) 
    {
?>             
                <heure>
                    <valeur><?=$humidite->heures?></valeur>
                    <min><?=$humidite->minimum?></min>
                    <moyenne><?=round($humidite->moyenne)?></moyenne>  
                    <max><?=$humidite->maximum?></max>  
                </heure>                  
<?php
    }
?>
        </jour>
    </bureau>     
</humidite>