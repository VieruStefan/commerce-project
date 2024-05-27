from fastapi import FastAPI
import router

def create_app()-> FastAPI:
    app = FastAPI(title="OferteDirecte")

    app.include_router(router.api_router)

    return app

app = create_app()