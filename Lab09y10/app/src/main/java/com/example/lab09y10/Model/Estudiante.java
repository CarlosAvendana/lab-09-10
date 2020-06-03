package com.example.lab09y10.Model;

import java.io.Serializable;

public class Estudiante implements Serializable {

    String _id;
    String _nombre;
    String _apellido;
    int _anios;

    public Estudiante() {
    }

    public Estudiante(String _id, String _nombre, String _apellido, int _anios) {
        this._id = _id;
        this._nombre = _nombre;
        this._apellido = _apellido;
        this._anios = _anios;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_apellido() {
        return _apellido;
    }

    public void set_apellido(String _apellido) {
        this._apellido = _apellido;
    }

    public int get_anios() {
        return _anios;
    }

    public void set_anios(int _anios) {
        this._anios = _anios;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "_id='" + _id + '\'' +
                ", _nombre='" + _nombre + '\'' +
                ", _apellido='" + _apellido + '\'' +
                ", _anios=" + _anios +
                '}';
    }
}
