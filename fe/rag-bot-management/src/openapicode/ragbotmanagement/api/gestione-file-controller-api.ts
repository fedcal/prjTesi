/* tslint:disable */
/* eslint-disable */
/**
 * Applicativo BFF
 * Strato applicativo tramite il quale si fa interfacciare i vari microservizi BE con l\'applicativo FE
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: f.calo29@studenti.uniba.it
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import type { Configuration } from '../configuration';
import type { AxiosPromise, AxiosInstance, RawAxiosRequestConfig } from 'axios';
import globalAxios from 'axios';
// Some imports not used depending on template conditions
// @ts-ignore
import { DUMMY_BASE_URL, assertParamExists, setApiKeyToObject, setBasicAuthToObject, setBearerAuthToObject, setOAuthToObject, setSearchParams, serializeDataIfNeeded, toPathString, createRequestFunction } from '../common';
// @ts-ignore
import { BASE_PATH, COLLECTION_FORMATS, type RequestArgs, BaseAPI, RequiredError, operationServerMap } from '../base';
// @ts-ignore
import type { GenericResponseDtoListPdfAddestratiDto } from '../model';
// @ts-ignore
import type { GenericResponseDtoString } from '../model';
/**
 * GestioneFileControllerApi - axios parameter creator
 * @export
 */
export const GestioneFileControllerApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * Specificando il nome del bot, si avvia l\'addestramento massivo
         * @summary Addestamento massivo bot
         * @param {string} nomeBot 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        addestramentoMassivo: async (nomeBot: string, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'nomeBot' is not null or undefined
            assertParamExists('addestramentoMassivo', 'nomeBot', nomeBot)
            const localVarPath = `/documenti-management/addestramento-massivo`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            if (nomeBot !== undefined) {
                localVarQueryParameter['nomeBot'] = nomeBot;
            }


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * Specificando il nome del bot e caricando un file, si avvia l\'addestramento per il singolo file
         * @summary Addestamento bot singolo file
         * @param {string} nomeBot 
         * @param {File} file 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        addestramentoSingolo: async (nomeBot: string, file: File, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'nomeBot' is not null or undefined
            assertParamExists('addestramentoSingolo', 'nomeBot', nomeBot)
            // verify required parameter 'file' is not null or undefined
            assertParamExists('addestramentoSingolo', 'file', file)
            const localVarPath = `/documenti-management/addestramento-singolo`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;
            const localVarFormParams = new ((configuration && configuration.formDataCtor) || FormData)();

            if (nomeBot !== undefined) {
                localVarQueryParameter['nomeBot'] = nomeBot;
            }


            if (file !== undefined) { 
                localVarFormParams.append('file', file as any);
            }
    
    
            localVarHeaderParameter['Content-Type'] = 'multipart/form-data';
    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = localVarFormParams;

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * GestioneFileControllerApi - functional programming interface
 * @export
 */
export const GestioneFileControllerApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = GestioneFileControllerApiAxiosParamCreator(configuration)
    return {
        /**
         * Specificando il nome del bot, si avvia l\'addestramento massivo
         * @summary Addestamento massivo bot
         * @param {string} nomeBot 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async addestramentoMassivo(nomeBot: string, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<GenericResponseDtoListPdfAddestratiDto>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.addestramentoMassivo(nomeBot, options);
            const localVarOperationServerIndex = configuration?.serverIndex ?? 0;
            const localVarOperationServerBasePath = operationServerMap['GestioneFileControllerApi.addestramentoMassivo']?.[localVarOperationServerIndex]?.url;
            return (axios, basePath) => createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration)(axios, localVarOperationServerBasePath || basePath);
        },
        /**
         * Specificando il nome del bot e caricando un file, si avvia l\'addestramento per il singolo file
         * @summary Addestamento bot singolo file
         * @param {string} nomeBot 
         * @param {File} file 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async addestramentoSingolo(nomeBot: string, file: File, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<GenericResponseDtoString>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.addestramentoSingolo(nomeBot, file, options);
            const localVarOperationServerIndex = configuration?.serverIndex ?? 0;
            const localVarOperationServerBasePath = operationServerMap['GestioneFileControllerApi.addestramentoSingolo']?.[localVarOperationServerIndex]?.url;
            return (axios, basePath) => createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration)(axios, localVarOperationServerBasePath || basePath);
        },
    }
};

/**
 * GestioneFileControllerApi - factory interface
 * @export
 */
export const GestioneFileControllerApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = GestioneFileControllerApiFp(configuration)
    return {
        /**
         * Specificando il nome del bot, si avvia l\'addestramento massivo
         * @summary Addestamento massivo bot
         * @param {GestioneFileControllerApiAddestramentoMassivoRequest} requestParameters Request parameters.
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        addestramentoMassivo(requestParameters: GestioneFileControllerApiAddestramentoMassivoRequest, options?: RawAxiosRequestConfig): AxiosPromise<GenericResponseDtoListPdfAddestratiDto> {
            return localVarFp.addestramentoMassivo(requestParameters.nomeBot, options).then((request) => request(axios, basePath));
        },
        /**
         * Specificando il nome del bot e caricando un file, si avvia l\'addestramento per il singolo file
         * @summary Addestamento bot singolo file
         * @param {GestioneFileControllerApiAddestramentoSingoloRequest} requestParameters Request parameters.
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        addestramentoSingolo(requestParameters: GestioneFileControllerApiAddestramentoSingoloRequest, options?: RawAxiosRequestConfig): AxiosPromise<GenericResponseDtoString> {
            return localVarFp.addestramentoSingolo(requestParameters.nomeBot, requestParameters.file, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * Request parameters for addestramentoMassivo operation in GestioneFileControllerApi.
 * @export
 * @interface GestioneFileControllerApiAddestramentoMassivoRequest
 */
export interface GestioneFileControllerApiAddestramentoMassivoRequest {
    /**
     * 
     * @type {string}
     * @memberof GestioneFileControllerApiAddestramentoMassivo
     */
    readonly nomeBot: string
}

/**
 * Request parameters for addestramentoSingolo operation in GestioneFileControllerApi.
 * @export
 * @interface GestioneFileControllerApiAddestramentoSingoloRequest
 */
export interface GestioneFileControllerApiAddestramentoSingoloRequest {
    /**
     * 
     * @type {string}
     * @memberof GestioneFileControllerApiAddestramentoSingolo
     */
    readonly nomeBot: string

    /**
     * 
     * @type {File}
     * @memberof GestioneFileControllerApiAddestramentoSingolo
     */
    readonly file: File
}

/**
 * GestioneFileControllerApi - object-oriented interface
 * @export
 * @class GestioneFileControllerApi
 * @extends {BaseAPI}
 */
export class GestioneFileControllerApi extends BaseAPI {
    /**
     * Specificando il nome del bot, si avvia l\'addestramento massivo
     * @summary Addestamento massivo bot
     * @param {GestioneFileControllerApiAddestramentoMassivoRequest} requestParameters Request parameters.
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof GestioneFileControllerApi
     */
    public addestramentoMassivo(requestParameters: GestioneFileControllerApiAddestramentoMassivoRequest, options?: RawAxiosRequestConfig) {
        return GestioneFileControllerApiFp(this.configuration).addestramentoMassivo(requestParameters.nomeBot, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * Specificando il nome del bot e caricando un file, si avvia l\'addestramento per il singolo file
     * @summary Addestamento bot singolo file
     * @param {GestioneFileControllerApiAddestramentoSingoloRequest} requestParameters Request parameters.
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof GestioneFileControllerApi
     */
    public addestramentoSingolo(requestParameters: GestioneFileControllerApiAddestramentoSingoloRequest, options?: RawAxiosRequestConfig) {
        return GestioneFileControllerApiFp(this.configuration).addestramentoSingolo(requestParameters.nomeBot, requestParameters.file, options).then((request) => request(this.axios, this.basePath));
    }
}

