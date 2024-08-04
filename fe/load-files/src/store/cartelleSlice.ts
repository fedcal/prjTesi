import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { MS_FILE_MANAGEMENT_CONFIG, STATUS_FULLFILLED, STATUS_PENDING, STATUS_REJECTED } from "./store-constants";
import { CartelleControllerApi, CartelleControllerApiAggiungiCartellaRequest, CartelleControllerApiProvaReactEndpointRequest } from "../openapicode/filemanagement";

export interface Response{
    aggiungiCartellaResult: String | null,
    rinominaCartellaResult: String | null,
    eliminaCartellaResult: String | null,
    statusResponse: String | null,
    messaggiResponse: String | null,
}

const initialState: Response = {
    aggiungiCartellaResult: '',
    rinominaCartellaResult: '',
    eliminaCartellaResult: '',
    statusResponse: '',
    messaggiResponse: '',
}


export const aggiungiCartella = createAsyncThunk(
    '/creaCartella',
    async (body: CartelleControllerApiAggiungiCartellaRequest) => {
        const api = new CartelleControllerApi(MS_FILE_MANAGEMENT_CONFIG);
        const response = await api.aggiungiCartella(body);
        return response.data;
    }
)

export const provaReactEndpoint = createAsyncThunk(
    '/prova',
    async ({body}: CartelleControllerApiProvaReactEndpointRequest) => {
        const api = new CartelleControllerApi(MS_FILE_MANAGEMENT_CONFIG);
        const response = await api.provaReactEndpoint({body});
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
            state.insertStatuus = STATUS_PENDING;
        });

        builder.addCase(aggiungiCartella.fulfilled,(state: any, action: any)=>{
            state.insertResult = action.payload.payload;
            state.insertStatuus = STATUS_FULLFILLED;
        });

        builder.addCase(aggiungiCartella.rejected,(state: any, action: any)=>{
            state.insertStatuus = STATUS_REJECTED;
        });

        builder.addCase(provaReactEndpoint.pending,(state: any, action: any)=>{
            state.insertStatuus = STATUS_PENDING;
        });

        builder.addCase(provaReactEndpoint.fulfilled,(state: any, action: any)=>{
            state.insertResult = action.payload.payload;
            state.insertStatuus = STATUS_FULLFILLED;
        });

        builder.addCase(provaReactEndpoint.rejected,(state: any, action: any)=>{
            state.insertStatuus = STATUS_REJECTED;
        });
    }
});

export default cartelleSlice.reducer;

