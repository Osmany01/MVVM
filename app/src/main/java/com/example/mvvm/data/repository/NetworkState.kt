package com.example.mvvm.data.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED

}

class NetworkState(val status: Status, val msg: String) {

    companion object {

        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")

            LOADING = NetworkState(Status.RUNNING, "Running")

            ERROR = NetworkState(Status.FAILED, "Ops something went wrong")
        }
    }
}