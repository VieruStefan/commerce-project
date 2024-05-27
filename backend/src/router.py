from fastapi import APIRouter
from api.v1.listings import router as listings_router
from api.v1.users import router as users_router

api_router = APIRouter()

api_router.include_router(users_router)
api_router.include_router(listings_router)