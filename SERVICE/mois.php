<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);

    $usager = 'postgres';
    $motdepasse = 'password';
    $hote = 'localhost';
    $base = 'maintenance';

    $dsn = 'pgsql:dbname='.$base.';host=' . $hote;
    $basededonnees = new PDO($dsn, $usager, $motdepasse);

    //$SQL_LISTE_HUMIDITE = "SELECT * FROM humidite";
    $SQL_LISTE_HUMIDITE = "SELECT date_part('day',moment) as jour, MAX(tauxhumidite) as maximum, MIN(tauxhumidite) as minimum,
    AVG(tauxhumidite) as moyenne FROM humidite WHERE date_part('month', moment) = date_part('month', moment) 
    GROUP BY date_part('day',moment)";
    $requete = $basededonnees->prepare($SQL_LISTE_HUMIDITE);
    $requete->execute();
    $humidites = $requete->fetchAll(PDO::FETCH_OBJ);

    $SQL_MOYENNES = "SELECT MAX(tauxhumidite) as maximum, MIN(tauxhumidite) as minimum, 
    AVG(tauxhumidite) as moyenne FROM humidite LIMIT 720";
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
            <mois>
                <min><?=$moyenne->minimum?></min>
                <moyenne><?=round($moyenne->moyenne)?></moyenne>  
                <max><?=$moyenne->maximum?></max> 
<?php
    }
    foreach ($humidites as $humidite) 
    {
?>         
                <jour>
                    <valeur><?=$humidite->jour?></valeur>
                    <min><?=$humidite->minimum?></min>
                    <moyenne><?=round($moyenne->moyenne)?></moyenne>  
                    <max><?=$humidite->maximum?></max>  
                </jour>        
<?php
    }
?>
                </mois>
            </bureau>
    </humidite>
