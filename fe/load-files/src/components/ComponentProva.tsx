import { useEffect, useState } from 'react'
import { provaReactEndpoint } from '../store/cartelleSlice';
import { useAppDispatch } from '../hooks/hook';




const ComponentProva: React.FC = () => {
    const dispatch = useAppDispatch();
    const body = 'ciao';

    useEffect(() => {

        dispatch(provaReactEndpoint({body})).unwrap().then( resp => {
          console.log(resp);
        })
      }, [])

    return(<>
    
    </>)
}

export default ComponentProva
