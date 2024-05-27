from fastapi import APIRouter


router = APIRouter(prefix="/listings", tags=["Listings"])

@router.get("", summary="listings")
def get_listings():
    return "Hello listing"

@router.get("{id}", summary="listing")
def get_listing():
    return "Hello listing"

@router.post("", summary="add_listings")
def post_listings():
    return "Hello listing"

@router.post("", summary="add_listings")
def post_listings():
    return "Hello listing"
