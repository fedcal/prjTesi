import {TypedUseSelectorHook, useDispatch, useSelector} from 'react-redux';
import {AppDispatch, RootState} from "../store/store";

// Modulo che esporta hook personalizzati per semplificare l'uso di react-redux nell'applicazione.
// useAppDispatch restituisce l'istanza di AppDispatch, che consente di fare il dispatch di azioni Redux
// useAppSelector è utilizato per accedere a parti specifiche dello stato definito nella RootState (stato globale)

export const useAppDispatch: () => AppDispatch = useDispatch;
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;