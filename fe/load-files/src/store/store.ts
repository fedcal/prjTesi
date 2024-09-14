import {configureStore} from "@reduxjs/toolkit";
import cartelleSlice from "./cartelleSlice";

export const store = configureStore({
    reducer: {
        cartella: cartelleSlice
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        // AxiosHeaders not serializeable
        serializableCheck: false
    }),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;

