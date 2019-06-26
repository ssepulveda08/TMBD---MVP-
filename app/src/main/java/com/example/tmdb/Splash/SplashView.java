package com.example.tmdb.Splash;

public interface SplashView {
    void dataChange(); //metodo para realizar la carga de datos
    void changeActivity(); //metodo para cambiar la actividad cuando los datos estebn cargados
    void onMessage(String msg);  //Metodo para lanzar alertas
}
