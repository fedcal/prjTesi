import {configureStore} from "@reduxjs/toolkit";
import bffSlice from "./bffSlice";

export const store = configureStore({
    reducer: {
        bff: bffSlice
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        // AxiosHeaders not serializeable
        serializableCheck: false
    }),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;