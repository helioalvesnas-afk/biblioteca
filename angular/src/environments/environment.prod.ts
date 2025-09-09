import { runtimeEnv } from '../app/core/env';

export const environment = {
  production: false,
  api: runtimeEnv.API_URL
};
