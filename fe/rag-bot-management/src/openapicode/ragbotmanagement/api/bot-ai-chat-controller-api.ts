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
import type { GenericResponseDtoResponseMessagePdfDto } from '../model';
// @ts-ignore
import type { GenericResponseDtoString } from '../model';
/**
 * BotAiChatControllerApi - axios parameter creator
 * @export
 */
export const BotAiChatControllerApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * Invio di un messaggio al bot AI sfruttando l\'LLM addestrato
         * @summary Chat addestrata
         * @param {string} messagge 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        chatAddestrata1: async (messagge: string, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'messagge' is not null or undefined
            assertParamExists('chatAddestrata1', 'messagge', messagge)
            const localVarPath = `/bot-ai-chat/chat-addestrata`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            if (messagge !== undefined) {
                localVarQueryParameter['messagge'] = messagge;
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
         * Invio di un messaggio al bot AI sfruttando l\'LLM non addestrato
         * @summary Chat normale
         * @param {string} messagge 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        normalChat1: async (messagge: string, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'messagge' is not null or undefined
            assertParamExists('normalChat1', 'messagge', messagge)
            const localVarPath = `/bot-ai-chat/normal-chat`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            if (messagge !== undefined) {
                localVarQueryParameter['messagge'] = messagge;
            }


    
            setSearchParams(localVarUrlObj, localVarQueryParameter);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * BotAiChatControllerApi - functional programming interface
 * @export
 */
export const BotAiChatControllerApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = BotAiChatControllerApiAxiosParamCreator(configuration)
    return {
        /**
         * Invio di un messaggio al bot AI sfruttando l\'LLM addestrato
         * @summary Chat addestrata
         * @param {string} messagge 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async chatAddestrata1(messagge: string, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<GenericResponseDtoResponseMessagePdfDto>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.chatAddestrata1(messagge, options);
            const localVarOperationServerIndex = configuration?.serverIndex ?? 0;
            const localVarOperationServerBasePath = operationServerMap['BotAiChatControllerApi.chatAddestrata1']?.[localVarOperationServerIndex]?.url;
            return (axios, basePath) => createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration)(axios, localVarOperationServerBasePath || basePath);
        },
        /**
         * Invio di un messaggio al bot AI sfruttando l\'LLM non addestrato
         * @summary Chat normale
         * @param {string} messagge 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async normalChat1(messagge: string, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<GenericResponseDtoString>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.normalChat1(messagge, options);
            const localVarOperationServerIndex = configuration?.serverIndex ?? 0;
            const localVarOperationServerBasePath = operationServerMap['BotAiChatControllerApi.normalChat1']?.[localVarOperationServerIndex]?.url;
            return (axios, basePath) => createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration)(axios, localVarOperationServerBasePath || basePath);
        },
    }
};

/**
 * BotAiChatControllerApi - factory interface
 * @export
 */
export const BotAiChatControllerApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = BotAiChatControllerApiFp(configuration)
    return {
        /**
         * Invio di un messaggio al bot AI sfruttando l\'LLM addestrato
         * @summary Chat addestrata
         * @param {BotAiChatControllerApiChatAddestrata1Request} requestParameters Request parameters.
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        chatAddestrata1(requestParameters: BotAiChatControllerApiChatAddestrata1Request, options?: RawAxiosRequestConfig): AxiosPromise<GenericResponseDtoResponseMessagePdfDto> {
            return localVarFp.chatAddestrata1(requestParameters.messagge, options).then((request) => request(axios, basePath));
        },
        /**
         * Invio di un messaggio al bot AI sfruttando l\'LLM non addestrato
         * @summary Chat normale
         * @param {BotAiChatControllerApiNormalChat1Request} requestParameters Request parameters.
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        normalChat1(requestParameters: BotAiChatControllerApiNormalChat1Request, options?: RawAxiosRequestConfig): AxiosPromise<GenericResponseDtoString> {
            return localVarFp.normalChat1(requestParameters.messagge, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * Request parameters for chatAddestrata1 operation in BotAiChatControllerApi.
 * @export
 * @interface BotAiChatControllerApiChatAddestrata1Request
 */
export interface BotAiChatControllerApiChatAddestrata1Request {
    /**
     * 
     * @type {string}
     * @memberof BotAiChatControllerApiChatAddestrata1
     */
    readonly messagge: string
}

/**
 * Request parameters for normalChat1 operation in BotAiChatControllerApi.
 * @export
 * @interface BotAiChatControllerApiNormalChat1Request
 */
export interface BotAiChatControllerApiNormalChat1Request {
    /**
     * 
     * @type {string}
     * @memberof BotAiChatControllerApiNormalChat1
     */
    readonly messagge: string
}

/**
 * BotAiChatControllerApi - object-oriented interface
 * @export
 * @class BotAiChatControllerApi
 * @extends {BaseAPI}
 */
export class BotAiChatControllerApi extends BaseAPI {
    /**
     * Invio di un messaggio al bot AI sfruttando l\'LLM addestrato
     * @summary Chat addestrata
     * @param {BotAiChatControllerApiChatAddestrata1Request} requestParameters Request parameters.
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof BotAiChatControllerApi
     */
    public chatAddestrata1(requestParameters: BotAiChatControllerApiChatAddestrata1Request, options?: RawAxiosRequestConfig) {
        return BotAiChatControllerApiFp(this.configuration).chatAddestrata1(requestParameters.messagge, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * Invio di un messaggio al bot AI sfruttando l\'LLM non addestrato
     * @summary Chat normale
     * @param {BotAiChatControllerApiNormalChat1Request} requestParameters Request parameters.
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof BotAiChatControllerApi
     */
    public normalChat1(requestParameters: BotAiChatControllerApiNormalChat1Request, options?: RawAxiosRequestConfig) {
        return BotAiChatControllerApiFp(this.configuration).normalChat1(requestParameters.messagge, options).then((request) => request(this.axios, this.basePath));
    }
}
