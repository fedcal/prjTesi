/* tslint:disable */
/* eslint-disable */
/**
 * MS File Management
 * Microservizio dedicato alla gestione dei documenti e delle cartelle per l\'addestramento dei bot
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: f.calo29@studenti.uniba.it
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


// May contain unused imports in some cases
// @ts-ignore
import type { Esito } from './esito';

/**
 * 
 * @export
 * @interface GenericResponseDtoString
 */
export interface GenericResponseDtoString {
    /**
     * 
     * @type {Esito}
     * @memberof GenericResponseDtoString
     */
    'esito'?: Esito;
    /**
     * 
     * @type {string}
     * @memberof GenericResponseDtoString
     */
    'payload'?: string;
}

