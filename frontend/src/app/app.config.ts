import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from "@angular/common/http";
import { loggingInterceptorRequest } from '../interceptors/logging-interceptor-request';
import { loggingInterceptorResponse } from '../interceptors/logging-interceptor-response';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    provideHttpClient(
      withFetch(),
      withInterceptors([loggingInterceptorRequest, loggingInterceptorResponse])
    ), provideAnimationsAsync(),
  ]
};
