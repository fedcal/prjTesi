import { FC, useCallback, useEffect, useState} from 'react'
import './listaFolder.css'
import { Select } from "design-react-kit";
import {elencoCartelle} from '../../../store/cartelleSlice';
import { useDispatch } from 'react-redux';
import { useAppDispatch, useAppSelector } from '../../../hooks/hook';
import { CartelleDto } from '../../../openapicode/filemanagement';


const ListaFolderComponent: FC = () => {

    const [cartellaSelected, setCartellaSelected] = useState<string>();

    const listaCartelleDto = useAppSelector((state: any) => state.cartella?.listaCartelle);
    const listaCartelleStatus = useAppSelector((state: any) => state.cartella?.statusResponse);
    const listaCartelleMessage = useAppSelector((state: any) => state.cartella?.messaggiResponse);
    
    let listCartelle: any = null ;
    const dispatch = useAppDispatch();

    {/*useEffect(()=>{
        dispatch(elencoCartelle()).then((res)=>{
            listCartelle = res.payload
            console.log(res)
        });
    });*/}

    {/*useState(()=>{
        dispatch(elencoCartelle()).then((res)=>{
            listCartelle = res.payload
            console.log(res)
        });
    })*/}
    {/*console.log('prima della chiamata lista:', listaCartelleDto)*/}
    useEffect(()=>{
        dispatch(elencoCartelle()).then((res)=>{
            listCartelle = res.payload
            console.log(res)
        });
    },[]);

   {/* console.log('dopo la chiamata lista:', listaCartelleDto)*/}

   const handleIndexSelect  = (value: any) => {
    console.log(value);
    setCartellaSelected(value);
   }

   if(cartellaSelected !== undefined){

   }

    return (
        <div className='lisft-folder'>
            <div className="line-separator"></div>
            <h3 className='title-section'>Lista Cartelle</h3>
            <div className='info-cartelle-section'>
                <select onChange={(e) => handleIndexSelect(e.target.value)} className='seleziona-cartella'>
                    <option value="Seleziona una cartella" key = {'Seleziona una cartella'}>Seleziona una cartella</option>
                    {listaCartelleDto.map((folder: CartelleDto, index: number) => <option key= {'Cartella' + index.toString()} accessKey={'Cartella' + index.toString()} value={(folder.nomeCartella ?? '') + (folder.idCartella ?? '')}>{folder.nomeCartella}</option>)}
                </select>
                <table className='table-info'>
                    <thead>
                        <tr>
                            <th>Nome Cartella</th>
                            <th>Percorso</th>
                            <th>Documenti</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Nome</td>
                            <td>Percorso</td>
                            <td>Documenti Contenuti</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
        </div>
    )
}

export default ListaFolderComponent
