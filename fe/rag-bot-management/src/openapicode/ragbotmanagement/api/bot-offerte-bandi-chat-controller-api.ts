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
 * BotOfferteBandiChatControllerApi - axios parameter creator
 * @export
 */
export const BotOfferteBandiChatControllerApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * Invio di un messaggio al bot OfferteBandi sfruttando l\'LLM addestrato
         * @summary Chat normale
         * @param {string} messagge 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        chatAddestrata: async (messagge: string, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'messagge' is not null or undefined
            assertParamExists('chatAddestrata', 'messagge', messagge)
            const localVarPath = `/bot-offerte-bandi-chat/chat-addestrata`;
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
         * Invio di un messaggio al bot OfferteBandi sfruttando l\'LLM non addestrato
         * @summary Chat normale
         * @param {string} messagge 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        normalChat: async (messagge: string, options: RawAxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'messagge' is not null or undefined
            assertParamExists('normalChat', 'messagge', messagge)
            const localVarPath = `/bot-offerte-bandi-chat/normal-chat`;
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
 * BotOfferteBandiChatControllerApi - functional programming interface
 * @export
 */
export const BotOfferteBandiChatControllerApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = BotOfferteBandiChatControllerApiAxiosParamCreator(configuration)
    return {
        /**
         * Invio di un messaggio al bot OfferteBandi sfruttando l\'LLM addestrato
         * @summary Chat normale
         * @param {string} messagge 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async chatAddestrata(messagge: string, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<GenericResponseDtoResponseMessagePdfDto>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.chatAddestrata(messagge, options);
            const localVarOperationServerIndex = configuration?.serverIndex ?? 0;
            const localVarOperationServerBasePath = operationServerMap['BotOfferteBandiChatControllerApi.chatAddestrata']?.[localVarOperationServerIndex]?.url;
            return (axios, basePath) => createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration)(axios, localVarOperationServerBasePath || basePath);
        },
        /**
         * Invio di un messaggio al bot OfferteBandi sfruttando l\'LLM non addestrato
         * @summary Chat normale
         * @param {string} messagge 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async normalChat(messagge: string, options?: RawAxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<GenericResponseDtoString>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.normalChat(messagge, options);
            const localVarOperationServerIndex = configuration?.serverIndex ?? 0;
            const localVarOperationServerBasePath = operationServerMap['BotOfferteBandiChatControllerApi.normalChat']?.[localVarOperationServerIndex]?.url;
            return (axios, basePath) => createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration)(axios, localVarOperationServerBasePath || basePath);
        },
    }
};

/**
 * BotOfferteBandiChatControllerApi - factory interface
 * @export
 */
export const BotOfferteBandiChatControllerApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = BotOfferteBandiChatControllerApiFp(configuration)
    return {
        /**
         * Invio di un messaggio al bot OfferteBandi sfruttando l\'LLM addestrato
         * @summary Chat normale
         * @param {BotOfferteBandiChatControllerApiChatAddestrataRequest} requestParameters Request parameters.
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        chatAddestrata(requestParameters: BotOfferteBandiChatControllerApiChatAddestrataRequest, options?: RawAxiosRequestConfig): AxiosPromise<GenericResponseDtoResponseMessagePdfDto> {
            return localVarFp.chatAddestrata(requestParameters.messagge, options).then((request) => request(axios, basePath));
        },
        /**
         * Invio di un messaggio al bot OfferteBandi sfruttando l\'LLM non addestrato
         * @summary Chat normale
         * @param {BotOfferteBandiChatControllerApiNormalChatRequest} requestParameters Request parameters.
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        normalChat(requestParameters: BotOfferteBandiChatControllerApiNormalChatRequest, options?: RawAxiosRequestConfig): AxiosPromise<GenericResponseDtoString> {
            return localVarFp.normalChat(requestParameters.messagge, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * Request parameters for chatAddestrata operation in BotOfferteBandiChatControllerApi.
 * @export
 * @interface BotOfferteBandiChatControllerApiChatAddestrataRequest
 */
export interface BotOfferteBandiChatControllerApiChatAddestrataRequest {
    /**
     * 
     * @type {string}
     * @memberof BotOfferteBandiChatControllerApiChatAddestrata
     */
    readonly messagge: string
}

/**
 * Request parameters for normalChat operation in BotOfferteBandiChatControllerApi.
 * @export
 * @interface BotOfferteBandiChatControllerApiNormalChatRequest
 */
export interface BotOfferteBandiChatControllerApiNormalChatRequest {
    /**
     * 
     * @type {string}
     * @memberof BotOfferteBandiChatControllerApiNormalChat
     */
    readonly messagge: string
}

/**
 * BotOfferteBandiChatControllerApi - object-oriented interface
 * @export
 * @class BotOfferteBandiChatControllerApi
 * @extends {BaseAPI}
 */
export class BotOfferteBandiChatControllerApi extends BaseAPI {
    /**
     * Invio di un messaggio al bot OfferteBandi sfruttando l\'LLM addestrato
     * @summary Chat normale
     * @param {BotOfferteBandiChatControllerApiChatAddestrataRequest} requestParameters Request parameters.
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof BotOfferteBandiChatControllerApi
     */
    public chatAddestrata(requestParameters: BotOfferteBandiChatControllerApiChatAddestrataRequest, options?: RawAxiosRequestConfig) {
        return BotOfferteBandiChatControllerApiFp(this.configuration).chatAddestrata(requestParameters.messagge, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * Invio di un messaggio al bot OfferteBandi sfruttando l\'LLM non addestrato
     * @summary Chat normale
     * @param {BotOfferteBandiChatControllerApiNormalChatRequest} requestParameters Request parameters.
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof BotOfferteBandiChatControllerApi
     */
    public normalChat(requestParameters: BotOfferteBandiChatControllerApiNormalChatRequest, options?: RawAxiosRequestConfig) {
        return BotOfferteBandiChatControllerApiFp(this.configuration).normalChat(requestParameters.messagge, options).then((request) => request(this.axios, this.basePath));
    }
}

