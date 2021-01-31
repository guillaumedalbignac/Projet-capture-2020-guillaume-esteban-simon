<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

class BaseDeDonnees{
  public static function getConnexion(){
    $usager = 'postgres';
    $motdepasse = 'password';
    $hote = 'localhost';
    $base = 'maintenance';
    $dsn = "pgsql:host=$hote;dbname=$base;";
    $connexion = new PDO($dsn, $usager, $motdepasse);
    return $connexion;
  }
}
?>