import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {  CartelleDto } from "../openapicode/ragbotmanagement";

export interface Response{
    aggiungiCartellaResult: CartelleDto | null,
    rinominaCartellaResult: CartelleDto | null,
    eliminaCartellaResult: String | null,
    listaCartelle: Array<CartelleDto> | null,
    findCartella: CartelleDto | null,
    statusResponse: String | null,
    messaggiResponse: String | null,
}

const initialState: Response = {
    aggiungiCartellaResult: null,
    rinominaCartellaResult: null,
    eliminaCartellaResult: '',
    listaCartelle: [],
    findCartella: null,
    statusResponse: '',
    messaggiResponse: '',
}





const bffSlice = createSlice({
    name: 'bffSlice',
    initialState: initialState,
    reducers: {
        reset: (state) => {
            state = initialState
        }
    },
    extraReducers: (builder) => {
       
    }
});

export default bffSlice.reducer;

