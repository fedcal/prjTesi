import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { MS_FILE_MANAGEMENT_CONFIG, STATUS_FULLFILLED, STATUS_PENDING, STATUS_REJECTED } from "./store-constants";
import { CartelleControllerApi, CartelleControllerApiAggiungiCartellaRequest, CartelleControllerApiEliminaCartellaRequest, CartelleControllerApiFindCartellaRequest, CartelleControllerApiRinominaCartellaRequest, CartelleDto } from "../openapicode/filemanagement";

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

export const aggiungiCartella = createAsyncThunk(
    '/cartelle/creaCartella',
    async (body: CartelleControllerApiAggiungiCartellaRequest) => {
        const api = new CartelleControllerApi(MS_FILE_MANAGEMENT_CONFIG);
        const response = await api.aggiungiCartella(body);
        return response.data;
    }
)

export const rinominaCartella = createAsyncThunk(
    '/cartelle/rinomina',
    async (body: CartelleControllerApiRinominaCartellaRequest) => {
        const api = new CartelleControllerApi(MS_FILE_MANAGEMENT_CONFIG);
        const response = await api.rinominaCartella(body);
        return response.data;
    }
)

export const eliminaCartella = createAsyncThunk(
    '/cartelle/elimina',
    async (body: CartelleControllerApiEliminaCartellaRequest) => {
        const api = new CartelleControllerApi(MS_FILE_MANAGEMENT_CONFIG);
        const response = await api.eliminaCartella(body);
        return response.data;
    }
)

export const elencoCartelle = createAsyncThunk(
    '/cartelle/elenco',
    async () => {
        const api = new CartelleControllerApi(MS_FILE_MANAGEMENT_CONFIG);
        const response = await api.elencoCartelle();
        return response.data;
    }
)

export const findCartella = createAsyncThunk(
    '/cartelle/elenco',
    async (body: CartelleControllerApiFindCartellaRequest) => {
        const api = new CartelleControllerApi(MS_FILE_MANAGEMENT_CONFIG);
        const response = await api.findCartella(body);
        return response.data;
    }
)




const cartelleSlice = createSlice({
    name: 'cartelle',
    initialState: initialState,
    reducers: {
        reset: (state) => {
            state = initialState
        }
    },
    extraReducers: (builder) => {
        builder.addCase(aggiungiCartella.pending,(state: any, action: any)=>{
            state.statusResponse = STATUS_PENDING;
        });

        builder.addCase(aggiungiCartella.fulfilled,(state: any, action: any)=>{
            state.aggiungiCartellaResult = action.payload.payload;
            state.statusResponse = STATUS_FULLFILLED;
        });

        builder.addCase(aggiungiCartella.rejected,(state: any, action: any)=>{
            state.statusResponse = STATUS_REJECTED;
        });

        builder.addCase(rinominaCartella.pending,(state: any, action: any)=>{
            state.statusResponse = STATUS_PENDING;
        });

        builder.addCase(rinominaCartella.fulfilled,(state: any, action: any)=>{
            state.rinominaCartellaResult = action.payload.payload;
            state.statusResponse = STATUS_FULLFILLED;
        });

        builder.addCase(rinominaCartella.rejected,(state: any, action: any)=>{
            state.statusResponse = STATUS_REJECTED;
        });

        builder.addCase(eliminaCartella.pending,(state: any, action: any)=>{
            state.statusResponse = STATUS_PENDING;
        });

        builder.addCase(eliminaCartella.fulfilled,(state: any, action: any)=>{
            state.eliminaCartellaResult = action.payload.payload;
            state.statusResponse = STATUS_FULLFILLED;
        });

        builder.addCase(eliminaCartella.rejected,(state: any, action: any)=>{
            state.statusResponse = STATUS_REJECTED;
        });

        builder.addCase(elencoCartelle.pending,(state: any, action: any)=>{
            state.statusResponse = STATUS_PENDING;
        });

        builder.addCase(elencoCartelle.fulfilled,(state: any, action: any)=>{
            state.listaCartelle = action.payload.payload;
            state.statusResponse = STATUS_FULLFILLED;
        });

        builder.addCase(elencoCartelle.rejected,(state: any, action: any)=>{
            state.statusResponse = STATUS_REJECTED;
        });

        builder.addCase(findCartella.pending,(state: any, action: any)=>{
            state.statusResponse = STATUS_PENDING;
        });

        builder.addCase(findCartella.fulfilled,(state: any, action: any)=>{
            state.listaCartelle = action.payload.payload;
            state.statusResponse = STATUS_FULLFILLED;
        });

        builder.addCase(findCartella.rejected,(state: any, action: any)=>{
            state.statusResponse = STATUS_REJECTED;
        });
    }
});

export default cartelleSlice.reducer;

