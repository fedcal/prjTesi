import { Configuration } from "../openapicode/ragbotmanagement";

export const STATUS_PENDING = 'PENDING';
export const STATUS_FULLFILLED = 'FULLFILLED';
export const STATUS_REJECTED = 'REJECTED';

export const MS_FILE_MANAGEMENT_CONFIG = new Configuration({
    basePath: process.env.REACT_APP_MS_FILE_MANAGEMENT
});
