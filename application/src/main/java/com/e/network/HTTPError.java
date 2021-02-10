package com.e.network;

public class HTTPError extends Exception
{

    public int _errorCode;

    public HTTPError(String message, int errorCode)
    {
        super(message);

        _errorCode = errorCode;
    }

    public int getErrorCode()
    {
        return _errorCode;
    }

}
