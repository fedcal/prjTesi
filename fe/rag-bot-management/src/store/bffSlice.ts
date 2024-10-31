import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {  CartelleDto } from "../openapicode/ragbotmanagement";

export interface Response{
    //ChatBotAi
    botAiChatNormaleResult: String | null,
    botAiChatAddestrataResult: String | null,

}

const initialState: Response = {
    botAiChatNormaleResult: '',
    botAiChatAddestrataResult: ''
}

export const botAiChatNormale = createAsyncThunk(
    '/bot-ai-chat/normal-chat',
    async(body:)=>{
        
    }
)





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

