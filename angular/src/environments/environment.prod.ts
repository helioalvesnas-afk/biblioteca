import { runtimeEnv } from '../app/core/env';

export const environment = {
  production: true,
  api: runtimeEnv.API_URL
};
