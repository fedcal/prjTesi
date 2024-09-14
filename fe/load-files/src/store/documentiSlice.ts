import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { MS_FILE_MANAGEMENT_CONFIG, STATUS_FULLFILLED, STATUS_PENDING, STATUS_REJECTED } from "./store-constants";
import { DocumentiControllerApi, DocumentiControllerApiUploadFileRequest, DocumentiDto } from "../openapicode/filemanagement";

export interface Response{
    aggiungiDocumentoResult: DocumentiDto | null,
    rinominaDocumenntoResult: DocumentiDto | null,
    eliminaDocumentoResult: String | null,
    listaDocumento: Array<DocumentiDto> | null,
    findDocumento: DocumentiDto | null,
    statusResponse: String | null,
    messaggiResponse: String | null,
}

const initialState: Response = {
    aggiungiDocumentoResult: null,
    rinominaDocumenntoResult: null,
    eliminaDocumentoResult: '',
    listaDocumento: [],
    findDocumento: null,
    statusResponse: '',
    messaggiResponse: '',
}

export const caricaDocumento = createAsyncThunk(
    '/documenti/upload',
    async (body: DocumentiControllerApiUploadFileRequest) => {
        const api = new DocumentiControllerApi(MS_FILE_MANAGEMENT_CONFIG);
        const response = await api.uploadFile(body);
        return response.data;
    }
)

const documentiSlice = createSlice({
    name: 'documenti',
    initialState: initialState,
    reducers: {
        reset: (state) => {
            state = initialState
        }
    },
    extraReducers: (builder) => {
        builder.addCase(caricaDocumento.pending,(state: any, action: any)=>{
            state.statusResponse = STATUS_PENDING;
        });

        builder.addCase(caricaDocumento.fulfilled,(state: any, action: any)=>{
            state.aggiungiDocumentoResult = action.payload.payload;
            state.statusResponse = STATUS_FULLFILLED;
        });

        builder.addCase(caricaDocumento.rejected,(state: any, action: any)=>{
            state.statusResponse = STATUS_REJECTED;
        });
    }
});

export default documentiSlice.reducer;

